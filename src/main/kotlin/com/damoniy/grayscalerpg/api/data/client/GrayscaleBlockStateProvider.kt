package com.damoniy.grayscalerpg.api.data.client

import com.damoniy.grayscalerpg.GrayScaleRPG
import com.damoniy.grayscalerpg.block.GrayBlocks
import net.minecraft.data.DataGenerator
import net.minecraftforge.client.model.generators.BlockStateProvider
import net.minecraftforge.common.data.ExistingFileHelper

class GrayscaleBlockStateProvider(dataGenerator: DataGenerator, existingFileHelper: ExistingFileHelper): BlockStateProvider(dataGenerator, GrayScaleRPG.ID, existingFileHelper) {
    override fun registerStatesAndModels() {
        simpleBlock(GrayBlocks.humusDirt.block)
        simpleBlock(GrayBlocks.purplePlanks.block)
    }
}