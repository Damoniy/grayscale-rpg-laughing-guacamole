package com.damoniy.grayscalerpg.block

import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.SnowBlock
import net.minecraft.tags.FluidTags
import net.minecraft.util.Direction
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IWorldReader
import net.minecraft.world.lighting.LightEngine
import net.minecraft.world.server.ServerWorld
import java.util.*

abstract class SpreadableHumusSnowyDirtBlock(blockType: BlockType, blockName: String) :
    HumusSnowyDirtBlock(blockType, blockName) {
    private fun canBeGrass(p_220257_0_: BlockState, p_220257_1_: IWorldReader, p_220257_2_: BlockPos): Boolean {
        val blockPos = p_220257_2_.above()
        val blockState = p_220257_1_.getBlockState(blockPos)
        return if (blockState.`is`(Blocks.SNOW) && blockState.getValue(SnowBlock.LAYERS) == 1) {
            true
        } else if (blockState.fluidState.amount == 8) {
            false
        } else {
            val i = LightEngine.getLightBlockInto(
                p_220257_1_,
                p_220257_0_,
                p_220257_2_,
                blockState,
                blockPos,
                Direction.UP,
                blockState.getLightBlock(p_220257_1_, blockPos)
            )
            i < p_220257_1_.maxLightLevel
        }
    }

    private fun canPropagate(p_220256_0_: BlockState, p_220256_1_: IWorldReader, p_220256_2_: BlockPos): Boolean {
        val blockpos = p_220256_2_.above()
        return canBeGrass(p_220256_0_, p_220256_1_, p_220256_2_) && !p_220256_1_.getFluidState(blockpos)
            .`is`(FluidTags.WATER)
    }

    override fun randomTick(
        p_225542_1_: BlockState,
        p_225542_2_: ServerWorld,
        p_225542_3_: BlockPos,
        p_225542_4_: Random
    ) {
        if (!canBeGrass(p_225542_1_, p_225542_2_, p_225542_3_)) {
            if (!p_225542_2_.isAreaLoaded(
                    p_225542_3_,
                    3
                )
            ) return  // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            p_225542_2_.setBlockAndUpdate(p_225542_3_, GrayBlocks.HUMUS_DIRT.defaultBlockState())
        } else {
            if (p_225542_2_.getMaxLocalRawBrightness(p_225542_3_.above()) >= 9) {
                val blockstate = defaultBlockState()
                for (i in 0..3) {
                    val blockpos = p_225542_3_.offset(
                        p_225542_4_.nextInt(3) - 1,
                        p_225542_4_.nextInt(5) - 3,
                        p_225542_4_.nextInt(3) - 1
                    )
                    if (p_225542_2_.getBlockState(blockpos).`is`(GrayBlocks.HUMUS_DIRT) && canPropagate(
                            blockstate,
                            p_225542_2_,
                            blockpos
                        )
                    ) {
                        p_225542_2_.setBlockAndUpdate(
                            blockpos, blockstate.setValue(
                                this.SNOWY, java.lang.Boolean.valueOf(
                                    p_225542_2_.getBlockState(blockpos.above()).`is`(
                                        Blocks.SNOW
                                    )
                                )
                            )
                        )
                    }
                }
            }
        }
    }
}