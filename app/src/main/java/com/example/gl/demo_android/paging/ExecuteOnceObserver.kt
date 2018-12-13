package com.example.gl.demo_android.paging

import com.example.gl.demo_android.utils.DemoLog
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class ExecuteOnceObserver<T>(val onExecuteOnceNext: (T) -> Unit = {},
                             val onExecuteOnceCompiler: () -> Unit = {},
                             val onExecuteOnceError: (Throwable) -> Unit = {})
    :Observer<T> {

    private var mDisposable: Disposable? = null

    override fun onComplete() {
        onExecuteOnceCompiler()
    }

    override fun onSubscribe(d: Disposable) {
        DemoLog.pagingLog("onSubscribe")
        mDisposable = d
    }

    override fun onNext(t: T) {
        try {
            DemoLog.pagingLog("onNext")
            onExecuteOnceNext(t)
            this.onComplete()
        }catch (e: Throwable) {
            this.onError(e)
        }finally {
            if (mDisposable != null && !mDisposable!!.isDisposed) {
                DemoLog.pagingLog("dispose")
                mDisposable!!.dispose()
            }
        }
    }

    override fun onError(e: Throwable) {
        onExecuteOnceError(e)
    }
}