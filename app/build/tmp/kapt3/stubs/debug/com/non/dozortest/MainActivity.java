package com.non.dozortest;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0014R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\r\u0010\b\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0012"}, d2 = {"Lcom/non/dozortest/MainActivity;", "Landroidx/activity/ComponentActivity;", "()V", "mainViewModel", "Lcom/non/dozortest/viewmodel/MainViewModel;", "getMainViewModel", "()Lcom/non/dozortest/viewmodel/MainViewModel;", "mainViewModel$delegate", "Lkotlin/Lazy;", "networkViewModel", "Lcom/non/dozortest/viewmodel/NetworkViewModel;", "getNetworkViewModel", "()Lcom/non/dozortest/viewmodel/NetworkViewModel;", "networkViewModel$delegate", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"})
public final class MainActivity extends androidx.activity.ComponentActivity {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy mainViewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy networkViewModel$delegate = null;
    
    public MainActivity() {
        super(0);
    }
    
    private final com.non.dozortest.viewmodel.MainViewModel getMainViewModel() {
        return null;
    }
    
    private final com.non.dozortest.viewmodel.NetworkViewModel getNetworkViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
}