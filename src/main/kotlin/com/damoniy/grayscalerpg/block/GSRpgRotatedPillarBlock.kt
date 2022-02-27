package com.damoniy.grayscalerpg.block

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.item.BlockItemUseContext
import net.minecraft.state.StateContainer
import net.minecraft.state.properties.BlockStateProperties
import net.minecraft.util.Direction
import net.minecraft.util.Rotation

abstract class GSRpgRotatedPillarBlock(blockType: BlockType, blockName: String): GrayBlock(blockType, blockName) {

    companion object {
        val AXIS = BlockStateProperties.AXIS
    }

    init {
        this.registerDefaultState(
            this.defaultBlockState().setValue<Direction.Axis, Direction.Axis>(AXIS, Direction.Axis.Y)
        )
    }

    override fun rotate(p_185499_1_: BlockState, p_185499_2_: Rotation?): BlockState? {
        return when (p_185499_2_) {
            Rotation.COUNTERCLOCKWISE_90, Rotation.CLOCKWISE_90 -> when (p_185499_1_.getValue(AXIS) as Direction.Axis) {
                Direction.Axis.X -> p_185499_1_.setValue(AXIS, Direction.Axis.Z)
                Direction.Axis.Z -> p_185499_1_.setValue(AXIS, Direction.Axis.X)
                else -> p_185499_1_
            }
            else -> p_185499_1_
        }
    }

    override fun createBlockStateDefinition(p_206840_1_: StateContainer.Builder<Block?, BlockState?>) {
        p_206840_1_.add(AXIS)
    }

    override fun getStateForPlacement(p_196258_1_: BlockItemUseContext): BlockState? {
        return this.defaultBlockState().setValue<Direction.Axis, Direction.Axis>(AXIS, p_196258_1_.clickedFace.axis)
    }
}
