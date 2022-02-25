package com.damoniy.grayscalerpg

import com.damoniy.grayscalerpg.api.GeneralRegister
import com.damoniy.grayscalerpg.api.data.client.ItemModelProvider
import com.damoniy.grayscalerpg.api.data.client.GrayscaleBlockStateProvider
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventBusSubscriber
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import thedarkcolour.kotlinforforge.forge.FORGE_BUS
import thedarkcolour.kotlinforforge.forge.MOD_BUS


@Mod(GrayScaleRPG.ID)
object GrayScaleRPG {
    const val ID: String = "grayscalerpg"

    val LOGGER: Logger = LogManager.getLogger()

    init {
        LOGGER.log(Level.INFO, "Hello world!")
        MOD_BUS.addListener(GrayScaleRPG::onClientSetup)
        FORGE_BUS.addListener(GrayScaleRPG::onServerAboutToStart)
    }

    // Fired on client Init Event
    private fun onClientSetup(event: FMLClientSetupEvent) {
        LOGGER.log(Level.INFO, "Initializing client...")
    }

    // Fired with forge global event bus
    private fun onServerAboutToStart(event: FMLServerAboutToStartEvent) {
        LOGGER.log(Level.INFO, "Server starting...")
    }

    @EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
    object ModEvents {
        @SubscribeEvent
        fun registerBlocks(event: RegistryEvent.Register<Block>) {
            GeneralRegister.registerBlocks(event.registry)
        }

        @SubscribeEvent
        fun registerItems(event: RegistryEvent.Register<Item>) {
            GeneralRegister.registerItems(event.registry)
        }
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    object DataGenerators {

        @SubscribeEvent
        fun gatherData(event: GatherDataEvent) {
            val eventGenerator = event.generator
            eventGenerator.addProvider(GrayscaleBlockStateProvider(eventGenerator, event.existingFileHelper))
            eventGenerator.addProvider(ItemModelProvider(eventGenerator, event.existingFileHelper))
        }
    }
}