package net.runedar.snr.blocks.blockentities;

import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.world.World;

/** Subinterface of BlockEntityProvider that reduces type checking and conversion boilerplate related to tickers
 *  @param <T> class of the block entity hosted by this block
 *  @author Daomephsta
 */
public interface BlockEntityHost<T extends BlockEntity> extends BlockEntityProvider
{
    @Override
    default <U extends BlockEntity> BlockEntityTicker<U> getTicker(
            World world, BlockState state, BlockEntityType<U> blockEntityType)
    {
        return coerceTicker(getBlockEntityType(), blockEntityType, getTickDelegate());
    }

    @SuppressWarnings("unchecked")
    default <A extends BlockEntity> BlockEntityTicker<A> coerceTicker(
            BlockEntityType<T> expected, BlockEntityType<A> actual, BlockEntityTicker<T> tickDelegate)
    {
        return tickDelegate != null && expected == actual
                ? (world, blockPos, blockState, blockEntity) ->
                tickDelegate.tick(world, blockPos, blockState, (T) blockEntity)
                : null;
    }

    BlockEntityType<T> getBlockEntityType();

    BlockEntityTicker<T> getTickDelegate();
}
