package com.damoniy.grayscalerpg.block

import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.IGrowable
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockReader
import net.minecraft.world.World
import net.minecraft.world.gen.feature.FlowersFeature
import net.minecraft.world.gen.feature.IFeatureConfig
import net.minecraft.world.server.ServerWorld
import java.util.*

class BlockLimeGrass(type: BlockType, blockName: String) : GrayBlock(type, blockName), IGrowable {
    override fun isValidBonemealTarget(
        reader: IBlockReader,
        pos: BlockPos,
        blockState: BlockState,
        isRandomBlock: Boolean,
    ): Boolean {
        return reader.getBlockState(pos.above()).isAir
    }

    override fun isBonemealSuccess(
        world: World,
        random: Random,
        pos: BlockPos,
        blockState: BlockState,
    ): Boolean {
        return true
    }

    override fun performBonemeal(
        worldServer: ServerWorld,
        random: Random,
        blockPos: BlockPos,
        blockState: BlockState,
    ) {
        val blockPos = blockPos.above()
        val blockstate = Blocks.GRASS.defaultBlockState()
        label48@ for (i in 0..127) {
            var blockpos1 = blockPos
            for (j in 0 until i / 16) {
                blockpos1 = blockpos1.offset(random.nextInt(3) - 1,
                    (random.nextInt(3) - 1) * random.nextInt(3) / 2,
                    random.nextInt(3) - 1)
                if (!worldServer.getBlockState(blockpos1.below()).`is`(this) || worldServer.getBlockState(blockpos1)
                        .isCollisionShapeFullBlock(worldServer, blockpos1)
                ) {
                    continue@label48
                }
            }
            val blockstate2 = worldServer.getBlockState(blockpos1)
            if (blockstate2.`is`(blockstate.block) && random.nextInt(10) == 0) {
                (blockstate.block as IGrowable).performBonemeal(worldServer, random, blockpos1, blockstate2)
            }
            if (blockstate2.isAir) {
                var blockstate1: BlockState
                blockstate1 = if (random.nextInt(8) == 0) {
                    val list = worldServer.getBiome(blockpos1).generationSettings.flowerFeatures
                    if (list.isEmpty()) {
                        continue
                    }
                    val configuredFeature = list[0]
                    val flowersFeature = configuredFeature.feature as FlowersFeature<IFeatureConfig>
                    flowersFeature.getRandomFlower(random, blockpos1, configuredFeature.config())
                } else {
                    blockstate
                }
                if (blockstate1.canSurvive(worldServer, blockpos1)) {
                    worldServer.setBlock(blockpos1, blockstate1, 3)
                }
            }
        }
    }
}
