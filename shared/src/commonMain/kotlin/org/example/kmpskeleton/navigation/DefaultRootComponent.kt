package org.example.kmpskeleton.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DelicateDecomposeApi
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import org.example.kmpskeleton.data.remote.entity.CharacterEntity
import org.koin.mp.KoinPlatform.getKoin


@OptIn(DelicateDecomposeApi::class)
class DefaultRootComponent(
    componentContext: ComponentContext,
    private val screenFactory: ScreenComponentFactory = getKoin().get()
) : RootComponent, ComponentContext by componentContext {

    private val nav = StackNavigation<Config>()

    private fun navigateToDetail(character: CharacterEntity) {
        nav.push(Config.CharDetail(character))
    }

    fun navigateToCharList() {
        nav.replaceAll(Config.CharList)
    }

    fun navigateToFavorites() {
        nav.replaceAll(Config.Favorites)
    }

    fun navigateToSettings() {
        nav.replaceAll(Config.Settings)
    }

    override val routerState: Value<ChildStack<*, RootComponent.Child>> = childStack(
        source = nav,
        serializer = Config.serializer(),
        initialConfiguration = Config.CharList,
        handleBackButton = true,
        childFactory = { config, componentContext ->
            screenFactory.create(config, componentContext, onFinished = { nav.pop() }, nav)
        }
    )
    override fun onBack() {
        nav.pop()
    }

    @Serializable
    sealed interface Config {

        @Serializable
        data object CharList : Config

        @Serializable
        data class CharDetail(val char: CharacterEntity) : Config

        @Serializable
        data object Favorites : Config

        @Serializable
        data object Settings : Config
    }
}