package com.example.merzlyakov.presentation.dialogs

import android.app.AlertDialog
import android.content.Context

object DialogUtil {
    // Диалог для вывода информации о различных ошибках
    fun errorDialog(context: Context, error: String) {
        val dialogBuilder = AlertDialog.Builder(context)

        dialogBuilder.setMessage(error)
            .setCancelable(false)
            .setNegativeButton("Закрыть") { dialog, _ ->
                dialog.cancel()
            }

        val alert = dialogBuilder.create()
        alert.setTitle("Ошибка")
        alert.show()
    }

    // Интерфейс для того, чтобы проще было реализовывать листненеры для диалоговых окон
    interface Listener {
        fun getData(name: String)
    }
}