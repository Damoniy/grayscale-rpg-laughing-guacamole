package com.damoniy.grayscalerpg.block

object GrayBlocks {
    private val blocks = ArrayList<GrayBlock>()

    fun register(block: GrayBlock) {
        blocks.add(block)
    }

    fun get(): ArrayList<GrayBlock> {
        return ArrayList(blocks)
    }

    val LIME_GRASS = LimeGrassBlock(BlockType.GRASS, "lime_grass")
    val HUMUS_DIRT = HumusDirtBlock(BlockType.DIRT, "humus_dirt")

    val PURPLE_PLANKS = BlockWoodPlanksBlock(BlockType.PLANKS, "black_wood_planks")
    val BLACK_WOOD_LOG = BlackWoodLogBlock(BlockType.LOG, "black_wood_log")
}