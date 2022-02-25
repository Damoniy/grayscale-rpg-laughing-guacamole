package com.damoniy.grayscalerpg.api.data.client

import com.damoniy.grayscalerpg.GrayScaleRPG
import com.damoniy.grayscalerpg.block.GrayBlock
import com.damoniy.grayscalerpg.block.GrayBlocks
import net.minecraft.block.Block
import net.minecraft.data.DataGenerator
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.model.generators.BlockStateProvider
import net.minecraftforge.client.model.generators.ConfiguredModel
import net.minecraftforge.client.model.generators.ModelFile
import net.minecraftforge.common.data.ExistingFileHelper


class GrayscaleBlockStateProvider(dataGenerator: DataGenerator, existingFileHelper: ExistingFileHelper) :
    BlockStateProvider(dataGenerator, GrayScaleRPG.ID, existingFileHelper) {
    override fun registerStatesAndModels() {
        simpleBlock(GrayBlocks.humusDirt.block)
        simpleBlock(GrayBlocks.purplePlanks.block)
        grassBlock(GrayBlocks.limeGrass.getModdedBlock())
    }

    fun grassBlock(block: GrayBlock) {
        val models = cubeBottomTop(block.blockName,
            resourceName(block, "side"),
            resourceName(block, "bottom"),
            resourceName(block, "top"))
        getVariantBuilder(block).partialState().setModels(ConfiguredModel(models))
    }

    fun cubeBottomTop(block: String, side: ResourceLocation, bottom: ResourceLocation, top: ResourceLocation): ModelFile {
        return models().cubeBottomTop(block, side, bottom, top)
    }

    fun resourceName(block: GrayBlock, sufix: String): ResourceLocation {
        return modLoc("block/${block.blockName}_$sufix")
    }
}