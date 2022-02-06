package com.damoniy.grayscalerpg.block

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
}