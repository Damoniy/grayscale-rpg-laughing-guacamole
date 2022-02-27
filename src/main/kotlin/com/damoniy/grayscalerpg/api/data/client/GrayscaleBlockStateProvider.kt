package com.damoniy.grayscalerpg.api.data.client

import com.damoniy.grayscalerpg.GrayScaleRPG
import com.damoniy.grayscalerpg.block.GSRpgRotatedPillarBlock
import com.damoniy.grayscalerpg.block.GrayBlock
import com.damoniy.grayscalerpg.block.GrayBlocks
import net.minecraft.data.DataGenerator
import net.minecraft.util.Direction
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.model.generators.BlockStateProvider
import net.minecraftforge.client.model.generators.ConfiguredModel
import net.minecraftforge.client.model.generators.ModelFile
import net.minecraftforge.common.data.ExistingFileHelper


class GrayscaleBlockStateProvider(dataGenerator: DataGenerator, existingFileHelper: ExistingFileHelper) :
    BlockStateProvider(dataGenerator, GrayScaleRPG.ID, existingFileHelper) {
    override fun registerStatesAndModels() {
        simpleBlock(GrayBlocks.HUMUS_DIRT.block)
        simpleBlock(GrayBlocks.PURPLE_PLANKS.block)

        grassBlock(GrayBlocks.LIME_GRASS.getModdedBlock())

        rawLogBlock(GrayBlocks.BLACK_WOOD_LOG)
    }

    fun grassBlock(block: GrayBlock) {
        val models = cubeBottomTop(block.blockName,
            resourceName(block, "side"),
            resourceName(block, "bottom"),
            resourceName(block, "top"))
        getVariantBuilder(block).partialState().setModels(ConfiguredModel(models))
    }

    fun logBlockModels(block: GSRpgRotatedPillarBlock, vertical: ModelFile, horizontal: ModelFile) {
        getVariantBuilder(block)
            .partialState().with(GSRpgRotatedPillarBlock.AXIS, Direction.Axis.Y)
            .modelForState().modelFile(vertical).addModel()
            .partialState().with(GSRpgRotatedPillarBlock.AXIS, Direction.Axis.Z)
            .modelForState().modelFile(horizontal).rotationX(90).addModel()
            .partialState().with(GSRpgRotatedPillarBlock.AXIS, Direction.Axis.X)
            .modelForState().modelFile(horizontal).rotationX(90).rotationY(90).addModel()
    }

    fun rotatedPillar(block: GSRpgRotatedPillarBlock, blockName: String, side: ResourceLocation, end: ResourceLocation) {
        logBlockModels(
            block,
            models().cubeColumn(blockName, side, end),
            models().cubeColumnHorizontal(blockName + "_horizontal", side, end)
        )
    }

    fun rawLogBlock(block: GSRpgRotatedPillarBlock) {
        rotatedPillar(block, block.blockName, resourceName(block, "side"), resourceName(block,"end"))
    }

    fun cubeBottomTop(block: String, side: ResourceLocation, bottom: ResourceLocation, top: ResourceLocation): ModelFile {
        return models().cubeBottomTop(block, side, bottom, top)
    }

    fun resourceName(block: GrayBlock, sufix: String): ResourceLocation {
        return modLoc("block/${block.blockName}_$sufix")
    }
}