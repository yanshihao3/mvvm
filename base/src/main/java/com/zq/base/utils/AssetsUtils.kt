package com.zq.base.utils

import android.content.Context
import android.content.res.AssetManager
import android.text.TextUtils
import com.zq.base.BaseApplication
import java.io.*

class AssetsUtils {
    companion object {

        /**
         * 从assets目录复制到素材中心在sd卡的目录下
         *
         * @param inputPath
         * @return 是否成功处理
         */
        fun copyFromAssetsToMaterialPath(
            inputPath: String,
            outputPath: String,
        ): Boolean {
            var inputPath = inputPath
            if (TextUtils.isEmpty(inputPath)) {
                return false
            }

            if (inputPath.endsWith(File.separator)) {
                inputPath = inputPath.substring(0, inputPath.length - 1)
            }

            //从assets复制
            val assetManager = BaseApplication.getApplication().assets

            val list: Array<String>?
            try {
                list = assetManager.list(inputPath)
            } catch (e: IOException) {
                return true
            }

            if (list != null) {
                if (list.isEmpty()) {
                    return false
                }
            }

            if (list != null) {
                for (fileName in list) {
                    copyAssetsListFile(assetManager, inputPath, fileName, outputPath)
                }
            }

            return true
        }


        private fun copyAssetsListFile(
            assetManager: AssetManager,
            input: String,
            fileName: String,
            output: String
        ) {
            try {
                val innerList = assetManager.list(input + File.separator + fileName)
                if (innerList.isNullOrEmpty()) {
                    copySingleFile(assetManager, input, fileName, output)
                } else {
                    for (innerFile in innerList) {
                        copyAssetsListFile(
                            assetManager,
                            input + File.separator + fileName,
                            innerFile,
                            output + File.separator + fileName
                        )
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }


        private fun copySingleFile(
            assetManager: AssetManager,
            input: String,
            fileName: String,
            output: String
        ): Boolean {
            val outFile = File(output, fileName)
            if (!outFile.parentFile.exists()) {
                outFile.parentFile.mkdirs()
            }

            var inputStream: InputStream? = null
            var out: OutputStream? = null

            try {
                inputStream = assetManager.open(input + File.separator + fileName)
                out = FileOutputStream(outFile)

                val buffer = ByteArray(1024)
                var read: Int = inputStream!!.read(buffer)
                while (read != -1) {
                    out.write(buffer, 0, read)
                    read = inputStream!!.read(buffer)
                }
            } catch (e: IOException) {
                return false
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close()
                    } catch (e: IOException) {

                    }

                }
                if (out != null) {
                    try {
                        out.flush()
                        out.close()
                    } catch (e: IOException) {

                    }

                }
            }

            return true
        }
    }

}