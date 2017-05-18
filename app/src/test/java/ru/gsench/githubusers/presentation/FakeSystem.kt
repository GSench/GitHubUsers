package ru.gsench.githubusers.presentation

import java.io.IOException
import java.net.URL
import java.util.ArrayDeque
import java.util.Queue
import java.util.concurrent.TimeUnit

import ru.gsench.githubusers.domain.SystemInterface
import ru.gsench.githubusers.domain.utils.HttpParams
import ru.gsench.githubusers.domain.utils.Pair
import ru.gsench.githubusers.domain.utils.function

/**
 * Created by grish on 08.05.2017.
 */

class FakeSystem : SystemInterface {

    private val requests = ArrayDeque<String>()

    fun addRequest(req: String) {
        requests.add(req)
    }

    fun doOnBackground(background: function<Void>) {
        Thread(Runnable { background.run() }).start()
    }

    fun doOnForeground(function: function<Void>) {
        Thread(Runnable { function.run() }).start()
    }

    @Throws(IOException::class)
    override fun httpGet(url: URL, params: HttpParams?): Pair<ByteArray, HttpParams> {
        if (requests == null) return null
        try {
            TimeUnit.SECONDS.sleep(2)
        } catch (e: InterruptedException) {
        }

        return Pair<ByteArray, HttpParams>(requests.poll().toByteArray(), null)
    }

    override fun getSavedStringArray(title: String, def: Array<String>?): Array<String> {
        return arrayOfNulls(0)
    }

    override fun saveStringArray(title: String, array: Array<String>) {

    }

    override fun removeSaved(str: String) {

    }

}
