package com.damoniy.grayscalerpg.block

import net.minecraft.block.Blocks

object GrayBlocks {
    private val blocks = ArrayList<GrayBlock>()

    fun register(block: GrayBlock) {
        blocks.add(block)
    }

    fun get(): ArrayList<GrayBlock> {
        return ArrayList(blocks)
    }

    val humusDirt = HumusDirtBlock(BlockType.DIRT, "humus_dirt")
    val purplePlanks = BlackWoodPlankBlock(BlockType.PLANKS, "black_wood_planks")
    val limeGrass = BlockLimeGrass(BlockType.GRASS, "lime_grass")
}