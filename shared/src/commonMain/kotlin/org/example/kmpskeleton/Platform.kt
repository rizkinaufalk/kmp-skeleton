package org.example.kmpskeleton

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform