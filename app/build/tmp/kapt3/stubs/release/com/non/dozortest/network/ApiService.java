package com.non.dozortest.network;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0003\u0010\u0007\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\tJ(\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00032\b\b\u0003\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\f\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\r\u00a8\u0006\u000e"}, d2 = {"Lcom/non/dozortest/network/ApiService;", "", "getMovieVideos", "Lretrofit2/Response;", "Lcom/non/dozortest/network/response/VideoResponse;", "movieId", "", "language", "", "(ILjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPopularMovies", "Lcom/non/dozortest/network/response/MovieResponse;", "page", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"})
public abstract interface ApiService {
    
    @retrofit2.http.GET(value = "popular")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPopularMovies(@retrofit2.http.Query(value = "language")
    @org.jetbrains.annotations.NotNull()
    java.lang.String language, @retrofit2.http.Query(value = "page")
    int page, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.non.dozortest.network.response.MovieResponse>> $completion);
    
    @retrofit2.http.GET(value = "movie/{movie_id}/videos")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getMovieVideos(@retrofit2.http.Path(value = "movie_id")
    int movieId, @retrofit2.http.Query(value = "language")
    @org.jetbrains.annotations.NotNull()
    java.lang.String language, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.non.dozortest.network.response.VideoResponse>> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}