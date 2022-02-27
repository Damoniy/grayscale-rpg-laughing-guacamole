package com.damoniy.grayscalerpg.block

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.SnowyDirtBlock
import net.minecraft.item.BlockItemUseContext
import net.minecraft.state.StateContainer
import net.minecraft.state.properties.BlockStateProperties
import net.minecraft.util.Direction
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IWorld
import java.lang.Boolean
import kotlin.String

abstract class HumusSnowyDirtBlock(blockType: BlockType, blockName: String) :
    GrayBlock(blockType, blockName) {

    val SNOWY = BlockStateProperties.SNOWY

    override fun updateShape(
        p_196271_1_: BlockState,
        p_196271_2_: Direction,
        p_196271_3_: BlockState,
        p_196271_4_: IWorld?,
        p_196271_5_: BlockPos?,
        p_196271_6_: BlockPos?
    ): BlockState? {
        return if (p_196271_2_ != Direction.UP) super.updateShape(
            p_196271_1_,
            p_196271_2_,
            p_196271_3_,
            p_196271_4_,
            p_196271_5_,
            p_196271_6_
        ) else p_196271_1_.setValue(
            SnowyDirtBlock.SNOWY, Boolean.valueOf(
                p_196271_3_.`is`(
                    Blocks.SNOW_BLOCK
                ) || p_196271_3_.`is`(Blocks.SNOW)
            )
        )
    }

    override fun getStateForPlacement(p_196258_1_: BlockItemUseContext): BlockState? {
        val blockstate = p_196258_1_.level.getBlockState(p_196258_1_.clickedPos.above())
        return defaultBlockState().setValue(
            SnowyDirtBlock.SNOWY, Boolean.valueOf(
                blockstate.`is`(Blocks.SNOW_BLOCK) || blockstate.`is`(
                    Blocks.SNOW
                )
            )
        )
    }

    override fun createBlockStateDefinition(p_206840_1_: StateContainer.Builder<Block?, BlockState?>) {
        p_206840_1_.add(SnowyDirtBlock.SNOWY)
    }
}