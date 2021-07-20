package com.example.appname

import android.annotation.SuppressLint
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

class FileSystemKt {
    @SuppressLint("SdCardPath")
    val storagePath = "/data/data/"
    val appStoragePath = storagePath + "com.example.appname/"
    val loginDataPath = appStoragePath + "login_data/"
    val scannerDataPath = appStoragePath + "files_data/"

    fun DirIsEmpty(dirName: String, excludeRoot: Boolean = false): Boolean {
        var filesCount = 0
        var fnDirName: String = dirName
        if(excludeRoot == false) {
            (appStoragePath + dirName).also { fnDirName = it }
        }

        try {
            File(fnDirName).walk().filter {
                it.isFile
            }.forEach {
                filesCount += 1
            }
        } catch(e: IOException) {
            println("\n\n----\n\nFn DirIsEmpty:\n\n----\n\n" + e.message)
        }
        return filesCount > 0
    }

    fun FilesInDir(dirName: String, excludeRoot: Boolean = false): Int {
        var filesCount = 0
        var fnDirName: String = dirName
        if(excludeRoot == false) {
            (appStoragePath + dirName).also { fnDirName = it }
        }

        try {
            File(fnDirName).walk().filter {
                it.isFile
            }.forEach {
                filesCount += 1
            }
        } catch(e: IOException) {
            println("\n\n----\n\nFn DirIsEmpty:\n\n----\n\n" + e.message)
        }
        return filesCount
    }

    fun MakeDir(dirName: String, excludeRoot: Boolean = false): Boolean {
        var fnDirName: String = dirName
        if(excludeRoot == false) {
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

    fun MakeFile(fileName: String, fileContent: Any, excludeRoot: Boolean = false): Boolean {
        var fnFileName: String = fileName
        if(excludeRoot == false) {
            (appStoragePath + fileName).also { fnFileName = it }
        }

        try {
            val newFile = File(fnFileName).writeText(fileContent.toString())
            if(this.FileEsists(fnFileName, excludeRoot)) {
                return true
            }
            return false
        } catch(e: IOException) {
            println("\n\n----\n\nFn MakeFile:\n\n----\n\n" + e.message)
        }
        return false
    }

    fun ReadFile(fileName: String, excludeRoot: Boolean = false): String {
        var fnFileName: String = fileName
        if(excludeRoot == false) {
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

    fun DirExists(dirName: String, excludeRoot: Boolean = false): Boolean {
        var fnDirName: String = dirName
        if(excludeRoot == false) {
            (appStoragePath + dirName).also { fnDirName = it }
        }

        val dirExists = File(fnDirName).exists()
        return dirExists
    }

    fun FileEsists(fileName: String, excludeRoot: Boolean = false): Boolean {
        var fnFileName: String = fileName
        if(excludeRoot == false) {
            (appStoragePath + fileName).also { fnFileName = it }
        }

        val fileExists = File(fnFileName).exists()
        return fileExists
    }
}
