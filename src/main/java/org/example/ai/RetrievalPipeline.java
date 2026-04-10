package org.example.ai;

import java.util.List;
import java.util.stream.Collectors;

public class RetrievalPipeline {

    public interface Retriever {
        List<RetrievedDocument> retrieve(String query, int topK);
    }

    public interface Reranker {
        List<RetrievedDocument> rerank(String query, List<RetrievedDocument> documents);
    }

    public record RetrievedDocument(String id, String content, double score) {
    }

    private final Retriever retriever;
    private final Reranker reranker;

    public RetrievalPipeline(Retriever retriever, Reranker reranker) {
        this.retriever = retriever;
        this.reranker = reranker;
    }

    public String buildContext(String query, int topK, int maxChars) {
        List<RetrievedDocument> retrieved = retriever.retrieve(query, topK);
        List<RetrievedDocument> reranked = reranker.rerank(query, retrieved);

        String context = reranked.stream()
                .map(RetrievedDocument::content)
                .collect(Collectors.joining("\n---\n"));

        if (context.length() <= maxChars) {
            return context;
        }
        return context.substring(0, maxChars);
    }
}
