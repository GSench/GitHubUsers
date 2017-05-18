package ru.gsench.githubusers.presentation

import android.app.Activity
import android.content.Context

import org.apache.commons.io.IOUtils

import java.io.BufferedInputStream
import java.io.EOFException
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.ArrayList
import java.util.Arrays
import java.util.HashSet

import ru.gsench.githubusers.domain.SystemInterface
import ru.gsench.githubusers.domain.utils.HttpParams
import ru.gsench.githubusers.domain.utils.Pair
import ru.gsench.githubusers.domain.utils.function

/**
 * Created by grish on 01.05.2017.
 */

class AndroidInterface(private val act: Activity?) : SystemInterface {

    override fun doOnBackground(background: ()->Unit) {
        Thread{background()}.start()
    }

    override fun doOnForeground(foreground: ()->Unit) {
        act?.runOnUiThread{foreground()}
    }

    @Throws(IOException::class)
    override fun httpGet(url: URL, params: HttpParams?): Pair<ByteArray, HttpParams> {
        val urlConnection = url.openConnection() as HttpURLConnection
        if (params != null) {
            for (header in params.headers!!) {
                urlConnection.setRequestProperty(header.t, header.u)
            }
        }
        try {
            //Opening input stream
            val `in` = BufferedInputStream(urlConnection.inputStream)

            //getting response parameters
            val responseCode = urlConnection.responseCode
            val headers = urlConnection.headerFields.entries.iterator()

            //proceeding response parameters
            val response = HttpParams()
            val headersParam = ArrayList<Pair<String, String>>()
            while (headers.hasNext()) {
                val entry = headers.next()
                headersParam.add(Pair(entry.key, entry.value[0]))
            }
            response.headers = headersParam
            response.resultCode = responseCode

            //reading input stream
            val available = `in`.available()
            if (available == 0)
                return Pair(readByByte(`in`), response)
            else
                return Pair(IOUtils.readFully(`in`, `in`.available()), response)
        } finally {
            urlConnection.disconnect()
        }
    }

    override fun getSavedStringArray(title: String, def: Array<String>?): Array<String> {
        val defaultArr: Set<String>?
        if (def == null)
            defaultArr = null
        else
            defaultArr = HashSet(Arrays.asList(*def))
        val stringSet = act!!.getSharedPreferences(SPREF, Context.MODE_PRIVATE).getStringSet(title, defaultArr)
        return stringSet.toTypedArray<String>()
    }

    override fun saveStringArray(title: String, array: Array<String>) {
        act!!.getSharedPreferences(SPREF, Context.MODE_PRIVATE)
                .edit()
                .putStringSet(title, HashSet(Arrays.asList(*array)))
                .commit()
    }

    @Throws(IOException::class)
    private fun readByByte(`in`: InputStream): ByteArray {
        val byteChain = ArrayList<Byte>()
        while (true) {
            try {
                byteChain.add(IOUtils.readFully(`in`, 1)[0])
            } catch (e: EOFException) {
                break
            }

        }
        val ret = ByteArray(byteChain.size)
        for (i in byteChain.indices) ret[i] = byteChain[i]
        return ret
    }

    override fun removeSaved(str: String) {
        act!!.getSharedPreferences(SPREF, Context.MODE_PRIVATE)
                .edit()
                .remove(str)
                .commit()
    }

    companion object {
        val SPREF = "preferences"
    }

}
