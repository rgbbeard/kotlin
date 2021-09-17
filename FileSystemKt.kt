package com.app.name

import android.annotation.SuppressLint
import android.content.Context
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

class FileSystemKt(context: Context) {
    @SuppressLint("SdCardPath")
    val storagePath = "/data/data/"
    final var appStoragePath: String
    final var loginDataPath: String
    final var scannerDataPath: String

    //For unknown reasons context.packageName (alias of getPackageName function) throws an exception
    init {
        try {
            this.appStoragePath = this.storagePath + context.packageName + "/"
        } catch(e: Exception) {
            this.appStoragePath = this.storagePath + "com.app.name/"
        }

        this.loginDataPath = appStoragePath + "login_data/"
        this.scannerDataPath = appStoragePath + "files_data/"
    }

    fun dirIsEmpty(dirName: String, excludeRoot: Boolean = false): Boolean {
        var filesCount = 0
        var fnDirName: String = dirName

        if(!excludeRoot) {
            (appStoragePath + dirName).also { fnDirName = it }
        }

        try {
            File(fnDirName).walk().filter {
                it.isFile
            }.forEach { _ ->
                filesCount += 1
            }
        } catch(e: IOException) {
            println("\n\n----\n\nFn DirIsEmpty:\n\n----\n\n" + e.message)
        }

        return filesCount > 0
    }

    fun filesInDir(dirName: String, excludeRoot: Boolean = false): Int {
        var filesCount = 0
        var fnDirName: String = dirName

        if(!excludeRoot) {
            (appStoragePath + dirName).also { fnDirName = it }
        }

        try {
            File(fnDirName).walk().filter {
                it.isFile
            }.forEach { _ ->
                filesCount += 1
            }
        } catch(e: IOException) {
            println("\n\n----\n\nFn DirIsEmpty:\n\n----\n\n" + e.message)
        }

        return filesCount
    }

    fun makeDir(dirName: String, excludeRoot: Boolean = false): Boolean {
        var fnDirName: String = dirName

        if(!excludeRoot) {
            (appStoragePath + dirName).also { fnDirName = it }
        }

        try {
            val newDir = File(fnDirName)
            val createDir = newDir.mkdir()

            if(createDir) {
                return true
            }
        } catch(e: IOException) {
            println("\n\n----\n\nFn MakeDir:\n\n----\n\n" + e.message)
        }

        return false
    }

    fun makeFile(fileName: String, fileContent: Any, excludeRoot: Boolean = false): Boolean {
        var fnFileName: String = fileName

        if(!excludeRoot) {
            (appStoragePath + fileName).also { fnFileName = it }
        }

        try {
            File(fnFileName).writeText(fileContent.toString())

            if(this.fileEsists(fnFileName, excludeRoot)) {
                return true
            }

            return false
        } catch(e: IOException) {
            println("\n\n----\n\nFn MakeFile:\n\n----\n\n" + e.message)
        }
        return false
    }

    fun readFile(fileName: String, excludeRoot: Boolean = false): String {
        var fnFileName: String = fileName
        if(!excludeRoot) {
            (appStoragePath + fileName).also { fnFileName = it }
        }

        try {
            val result: String
            val lines: List<String> = File(fnFileName).readLines()
            result = lines.joinToString()

            return result
        } catch(e: IOException) {
            println("\n\n----\n\nFn MakeFile IOException:\n\n----\n\n" + e.message)
        } catch(e: FileNotFoundException) {
            println("\n\n----\n\nFn MakeFile FileNotFoundException:\n\n----\n\n" + e.message)
        }
        return ""
    }

    fun dirExists(dirName: String, excludeRoot: Boolean = false): Boolean {
        var fnDirName: String = dirName

        if(!excludeRoot) {
            (appStoragePath + dirName).also { fnDirName = it }
        }

        return File(fnDirName).exists()
    }

    fun fileEsists(fileName: String, excludeRoot: Boolean = false): Boolean {
        var fnFileName: String = fileName

        if(!excludeRoot) {
            (appStoragePath + fileName).also { fnFileName = it }
        }

        return File(fnFileName).exists()
    }
}
