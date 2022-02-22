package com.damoniy.grayscalerpg.api.data.client

import com.damoniy.grayscalerpg.GrayScaleRPG
import com.damoniy.grayscalerpg.block.GrayBlocks
import net.minecraft.data.DataGenerator
import net.minecraftforge.client.model.generators.ItemModelProvider
import net.minecraftforge.common.data.ExistingFileHelper

class GrayscaleBlockModelProvider(generator: DataGenerator, existingFileHelper: ExistingFileHelper): ItemModelProvider(generator, GrayScaleRPG.ID, existingFileHelper) {

    override fun registerModels() {
        withExistingParent("item_humus_dirt", modLoc("block/block_humus_dirt"))
        withExistingParent("item_black_wood_planks", modLoc("block/block_black_wood_planks"))
    }
}