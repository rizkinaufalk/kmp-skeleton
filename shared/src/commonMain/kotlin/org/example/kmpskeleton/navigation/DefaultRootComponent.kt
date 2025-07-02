package org.example.kmpskeleton.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DelicateDecomposeApi
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
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
//    private val characterDB: RickAppDB = getKoin().get()
//
//    private val charDetailFactory: CharDetailComponentFactory = CharDetailComponentFactoryImpl()

    private fun navigateToDetail(character: CharacterEntity) {
        nav.push(Config.CharDetail(character))
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

//    private fun child(
//        config: Config,
//        componentContext: ComponentContext
//    ): RootComponent.Child = when (config) {
//        Config.CharList -> {
//            RootComponent.Child.CharList(
//                DefaultCharListComponent(
//                    componentContext = componentContext,
//                    getCharacterUseCase = getCharacterUseCase,
//                    characterDB,
//                    charClicked = { char -> nav.push(Config.CharDetail(char, )) }
//                )
//            )
//        }
//
//        is Config.CharDetail -> {
//            RootComponent.Child.CharDetail(
//                charDetailFactory.create(
//                    componentContext = componentContext,
//                    character = config.char,
//                    onFinished = { nav.pop() }
//                )
//            )
//        }
//    }

    override fun onBack() {
        nav.pop()
    }

    @Serializable
    sealed interface Config {

        @Serializable
        object CharList : Config

        @Serializable
        data class CharDetail(val char: CharacterEntity) : Config
    }
}