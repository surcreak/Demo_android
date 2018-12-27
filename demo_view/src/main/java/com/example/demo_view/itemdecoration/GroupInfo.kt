package com.example.demo_view.itemdecoration

class GroupInfo(val groupId: Int, val title: String, var positon: Int, var groupLenth: Int) {

    fun isFirstViewInGroup(): Boolean {
        return positon == 0
    }
    fun isLastViewInGroup(): Boolean {
        return positon == groupLenth - 1
    }
}