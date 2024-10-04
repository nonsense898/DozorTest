package com.non.dozortest.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0017\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cJ\u000e\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u0007J\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u00112\u0006\u0010\u001b\u001a\u00020\u001cJ\u000e\u0010!\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00020\u0007J\u000e\u0010#\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00020\u0007J\u000e\u0010$\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u0007R\u0016\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\r0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001d\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00120\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000fR#\u0010\u0017\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\t0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000f\u00a8\u0006%"}, d2 = {"Lcom/non/dozortest/viewmodel/MainViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/non/dozortest/repository/MovieRepository;", "(Lcom/non/dozortest/repository/MovieRepository;)V", "_selectedMovieEntity", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/non/dozortest/data/entities/Movie;", "_videoDetails", "", "", "allMovies", "Lkotlinx/coroutines/flow/StateFlow;", "", "getAllMovies", "()Lkotlinx/coroutines/flow/StateFlow;", "movies", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/paging/PagingData;", "getMovies", "()Lkotlinx/coroutines/flow/Flow;", "selectedMovieEntity", "getSelectedMovieEntity", "videoDetails", "getVideoDetails", "getMovieVideos", "", "movieId", "", "insertMovie", "movieEntity", "isMovieSaved", "", "removeMovie", "movie", "selectMovie", "updateMovie", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public class MainViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.non.dozortest.repository.MovieRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<androidx.paging.PagingData<com.non.dozortest.data.entities.Movie>> movies = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.non.dozortest.data.entities.Movie>> allMovies = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.non.dozortest.data.entities.Movie> _selectedMovieEntity = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.non.dozortest.data.entities.Movie> selectedMovieEntity = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.Map<java.lang.String, java.lang.String>> _videoDetails = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.Map<java.lang.String, java.lang.String>> videoDetails = null;
    
    @javax.inject.Inject()
    public MainViewModel(@org.jetbrains.annotations.NotNull()
    com.non.dozortest.repository.MovieRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<androidx.paging.PagingData<com.non.dozortest.data.entities.Movie>> getMovies() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.non.dozortest.data.entities.Movie>> getAllMovies() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.non.dozortest.data.entities.Movie> getSelectedMovieEntity() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.Map<java.lang.String, java.lang.String>> getVideoDetails() {
        return null;
    }
    
    public final void getMovieVideos(int movieId) {
    }
    
    public final void selectMovie(@org.jetbrains.annotations.NotNull()
    com.non.dozortest.data.entities.Movie movie) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Boolean> isMovieSaved(int movieId) {
        return null;
    }
    
    public final void insertMovie(@org.jetbrains.annotations.NotNull()
    com.non.dozortest.data.entities.Movie movieEntity) {
    }
    
    public final void updateMovie(@org.jetbrains.annotations.NotNull()
    com.non.dozortest.data.entities.Movie movieEntity) {
    }
    
    public final void removeMovie(@org.jetbrains.annotations.NotNull()
    com.non.dozortest.data.entities.Movie movie) {
    }
}