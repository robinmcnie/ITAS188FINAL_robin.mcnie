package edu.itas.itas188finalproject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface OpenAIApiService {
    @Headers("Authorization: Bearer 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")
    @POST("https://api.openai.com/v1/engines/text-davinci-002/completions")
    Call<OpenAIResponse> getRewrittenText(@Body OpenAIPrompt prompt);
}

