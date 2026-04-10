package org.example.ai;

import java.util.Map;

public class ToolAgentFacade {

    public interface Tool {
        String execute(String input);
    }

    private final RetrievalPipeline retrievalPipeline;
    private final LlmClient llmClient;
    private final Map<String, Tool> tools;

    public ToolAgentFacade(RetrievalPipeline retrievalPipeline, LlmClient llmClient, Map<String, Tool> tools) {
        this.retrievalPipeline = retrievalPipeline;
        this.llmClient = llmClient;
        this.tools = tools;
    }

    public String answer(String userQuery) {
        if (userQuery.startsWith("tool:")) {
            return invokeTool(userQuery);
        }

        String context = retrievalPipeline.buildContext(userQuery, 3, 500);
        String prompt = new PromptBuilder("Use context to answer.\\nContext:\\n{{context}}\\nQuestion: {{question}}")
                .with("context", context)
                .with("question", userQuery)
                .build();
        return llmClient.complete(prompt);
    }

    private String invokeTool(String query) {
        String[] parts = query.split(":", 3);
        if (parts.length < 3) {
            throw new IllegalArgumentException("Tool query must be tool:<name>:<input>");
        }
        Tool tool = tools.get(parts[1]);
        if (tool == null) {
            throw new IllegalArgumentException("Unknown tool: " + parts[1]);
        }
        return tool.execute(parts[2]);
    }
}
