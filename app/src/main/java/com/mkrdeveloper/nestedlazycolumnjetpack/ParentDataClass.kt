package com.mkrdeveloper.nestedlazycolumnjetpack

data class ParentDataClass(
    val title: String,
    val childList: List<ChildDataClass>
)
