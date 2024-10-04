package com.non.dozortest.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u000fJ\u000e\u0010\u0010\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\u0011J\u001c\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\r\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u000fJ\u0012\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00170\u0016J\u0016\u0010\u0019\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00180\u00162\u0006\u0010\u001a\u001a\u00020\u000eJ\u0012\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u001c0\u0016J\u0016\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\u0018H\u0086@\u00a2\u0006\u0002\u0010\u001fJ\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u00162\u0006\u0010\r\u001a\u00020\u000eJ\u0016\u0010\"\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\u0018H\u0086@\u00a2\u0006\u0002\u0010\u001fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006#"}, d2 = {"Lcom/non/dozortest/repository/MovieRepository;", "", "apiService", "Lcom/non/dozortest/network/ApiService;", "context", "Landroid/content/Context;", "(Lcom/non/dozortest/network/ApiService;Landroid/content/Context;)V", "movieDao", "Lcom/non/dozortest/dao/MovieDao;", "roomDatabase", "Lcom/non/dozortest/database/AppDatabase;", "deleteMovieById", "", "movieId", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteMovies", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchMovieVideos", "Lretrofit2/Response;", "Lcom/non/dozortest/network/response/VideoResponse;", "getAllMovies", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/non/dozortest/data/entities/Movie;", "getMovieById", "id", "getUsers", "Landroidx/paging/PagingData;", "insertMovie", "movie", "(Lcom/non/dozortest/data/entities/Movie;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isMovieSaved", "", "updateMovie", "app_debug"})
public final class MovieRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.non.dozortest.network.ApiService apiService = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.non.dozortest.database.AppDatabase roomDatabase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.non.dozortest.dao.MovieDao movieDao = null;
    
    @javax.inject.Inject()
    public MovieRepository(@org.jetbrains.annotations.NotNull()
    com.non.dozortest.network.ApiService apiService, @dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.non.dozortest.data.entities.Movie>> getAllMovies() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Boolean> isMovieSaved(int movieId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<androidx.paging.PagingData<com.non.dozortest.data.entities.Movie>> getUsers() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.non.dozortest.data.entities.Movie> getMovieById(int id) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteMovies(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object fetchMovieVideos(int movieId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.non.dozortest.network.response.VideoResponse>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertMovie(@org.jetbrains.annotations.NotNull()
    com.non.dozortest.data.entities.Movie movie, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateMovie(@org.jetbrains.annotations.NotNull()
    com.non.dozortest.data.entities.Movie movie, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteMovieById(int movieId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}