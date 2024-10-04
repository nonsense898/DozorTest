package com.non.dozortest.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0014\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nH\'J\u0018\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\n2\u0006\u0010\u0006\u001a\u00020\u0007H\'J\u0016\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010\u0010J\u001c\u0010\u0011\u001a\u00020\u00032\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u00a7@\u00a2\u0006\u0002\u0010\u0013J\u0016\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\n2\u0006\u0010\u0016\u001a\u00020\u0007H\'J\u0016\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010\u0010\u00a8\u0006\u0018"}, d2 = {"Lcom/non/dozortest/dao/MovieDao;", "", "deleteAll", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteMovie", "id", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllMovies", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/non/dozortest/data/entities/Movie;", "getMovieById", "insertMovie", "movieEntity", "(Lcom/non/dozortest/data/entities/Movie;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertMovies", "movies", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isMovieSaved", "", "movieId", "updateMovie", "app_release"})
@androidx.room.Dao()
public abstract interface MovieDao {
    
    @androidx.room.Query(value = "DELETE FROM movies")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT EXISTS(SELECT 1 FROM movies WHERE id = :movieId)")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.lang.Boolean> isMovieSaved(int movieId);
    
    @androidx.room.Query(value = "SELECT * FROM movies")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.non.dozortest.data.entities.Movie>> getAllMovies();
    
    @androidx.room.Query(value = "SELECT * FROM movies WHERE id = :id LIMIT 1")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.non.dozortest.data.entities.Movie> getMovieById(int id);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertMovie(@org.jetbrains.annotations.NotNull()
    com.non.dozortest.data.entities.Movie movieEntity, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateMovie(@org.jetbrains.annotations.NotNull()
    com.non.dozortest.data.entities.Movie movieEntity, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertMovies(@org.jetbrains.annotations.NotNull()
    java.util.List<com.non.dozortest.data.entities.Movie> movies, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM movies WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteMovie(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}