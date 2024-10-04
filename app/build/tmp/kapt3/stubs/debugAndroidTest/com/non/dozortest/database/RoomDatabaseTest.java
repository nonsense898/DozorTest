package com.non.dozortest.database;

@org.junit.runner.RunWith(value = androidx.test.ext.junit.runners.AndroidJUnit4.class)
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0007J\b\u0010\r\u001a\u00020\fH\u0007J\b\u0010\u000e\u001a\u00020\fH\u0007J\b\u0010\u000f\u001a\u00020\fH\u0017J\b\u0010\u0010\u001a\u00020\fH\u0007R\u0013\u0010\u0003\u001a\u00020\u00048G\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/non/dozortest/database/RoomDatabaseTest;", "Ljunit/framework/TestCase;", "()V", "instantTaskExecutorRule", "Landroidx/arch/core/executor/testing/InstantTaskExecutorRule;", "getInstantTaskExecutorRule", "()Landroidx/arch/core/executor/testing/InstantTaskExecutorRule;", "movieDao", "Lcom/non/dozortest/dao/MovieDao;", "roomDatabase", "Lcom/non/dozortest/database/AppDatabase;", "closeDb", "", "deleteAndVerifyMovie", "insertAndRetrieveMovie", "setUp", "updateAndRetrieveMovie", "app_debugAndroidTest"})
public final class RoomDatabaseTest extends junit.framework.TestCase {
    @org.jetbrains.annotations.NotNull()
    private final androidx.arch.core.executor.testing.InstantTaskExecutorRule instantTaskExecutorRule = null;
    private com.non.dozortest.database.AppDatabase roomDatabase;
    private com.non.dozortest.dao.MovieDao movieDao;
    
    public RoomDatabaseTest() {
        super();
    }
    
    @org.junit.Rule()
    @org.jetbrains.annotations.NotNull()
    public final androidx.arch.core.executor.testing.InstantTaskExecutorRule getInstantTaskExecutorRule() {
        return null;
    }
    
    @org.junit.Before()
    @java.lang.Override()
    public void setUp() {
    }
    
    @org.junit.After()
    public final void closeDb() {
    }
    
    @org.junit.Test()
    public final void insertAndRetrieveMovie() {
    }
    
    @org.junit.Test()
    public final void deleteAndVerifyMovie() {
    }
    
    @org.junit.Test()
    public final void updateAndRetrieveMovie() {
    }
}