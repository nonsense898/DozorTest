
@org.junit.runner.RunWith(value = androidx.test.ext.junit.runners.AndroidJUnit4.class)
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0007J\b\u0010\r\u001a\u00020\fH\u0007J\b\u0010\u000e\u001a\u00020\fH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u0013\u0010\u0005\u001a\u00020\u00068G\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"LMovieDaoTest;", "", "()V", "database", "Lcom/non/dozortest/database/AppDatabase;", "instantTaskExecutorRule", "Landroidx/arch/core/executor/testing/InstantTaskExecutorRule;", "getInstantTaskExecutorRule", "()Landroidx/arch/core/executor/testing/InstantTaskExecutorRule;", "movieDao", "Lcom/non/dozortest/dao/MovieDao;", "insertAndRetrieveMovie", "", "setUp", "tearDown", "app_debugAndroidTest"})
public final class MovieDaoTest {
    @org.jetbrains.annotations.NotNull()
    private final androidx.arch.core.executor.testing.InstantTaskExecutorRule instantTaskExecutorRule = null;
    private com.non.dozortest.database.AppDatabase database;
    private com.non.dozortest.dao.MovieDao movieDao;
    
    public MovieDaoTest() {
        super();
    }
    
    @org.junit.Rule()
    @org.jetbrains.annotations.NotNull()
    public final androidx.arch.core.executor.testing.InstantTaskExecutorRule getInstantTaskExecutorRule() {
        return null;
    }
    
    @org.junit.Before()
    public final void setUp() {
    }
    
    @org.junit.After()
    public final void tearDown() {
    }
    
    @org.junit.Test()
    public final void insertAndRetrieveMovie() {
    }
}