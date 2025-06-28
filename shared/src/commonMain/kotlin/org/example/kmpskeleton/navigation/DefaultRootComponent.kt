package org.example.kmpskeleton.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DelicateDecomposeApi
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import org.example.kmpskeleton.data.remote.entity.CharacterEntity
import org.example.kmpskeleton.domain.usecase.GetCharacterUseCase
import org.example.kmpskeleton.features.character.DefaultCharListComponent
import org.example.kmpskeleton.features.character.details.DefaultCharDetailComponent


@OptIn(DelicateDecomposeApi::class)
class DefaultRootComponent(
    componentContext: ComponentContext,
    private val getCharacterUseCase: GetCharacterUseCase
) : RootComponent, ComponentContext by componentContext {

    private val nav = StackNavigation<Config>()

    override val routerState: Value<ChildStack<*, RootComponent.Child>> = childStack(
        source = nav,
        serializer = Config.serializer(),
        initialConfiguration = Config.CharList,
        handleBackButton = true,
        childFactory = ::child
    )
    private fun child(
        config: Config,
        componentContext: ComponentContext
    ): RootComponent.Child = when (config) {
        Config.CharList -> {
            RootComponent.Child.CharList(
                DefaultCharListComponent(
                    componentContext = componentContext,
                    getCharacterUseCase = getCharacterUseCase,
                    charClicked = { char -> nav.push(Config.CharDetail(char)) }
                )
            )
        }

        is Config.CharDetail -> {
            RootComponent.Child.CharDetail(
                DefaultCharDetailComponent(
                    componentContext = componentContext,
                    character = config.char,
                    onFinished = { nav.pop() }
                )
            )
        }
    }

    override fun onBack() {
        nav.pop()
    }

    @Serializable
    private sealed interface Config {

        @Serializable
        object CharList : Config

        @Serializable
        data class CharDetail(val char: CharacterEntity) : Config
    }
}