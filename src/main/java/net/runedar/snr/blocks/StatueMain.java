package net.runedar.snr.blocks;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.runedar.snr.blocks.blockentities.BlockEntityHost;
import net.runedar.snr.blocks.blockentities.StatueBlockEntity;
import net.runedar.snr.registry.ModBlocks;
import net.runedar.snr.registry.ModItems;

@SuppressWarnings("deprecation")
public class StatueMain extends BlockWithEntity implements Waterloggable, BlockEntityHost<StatueBlockEntity> {

    public static final BooleanProperty WATERLOGGED;
	public static final DirectionProperty FACING;
    static VoxelShape VOXEL_SHAPE_CUBE;

      public StatueMain(Settings settings) {
        super(settings);
      //Rotation + Visual Fix
      setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH).with(WATERLOGGED, false));
      }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(Properties.HORIZONTAL_FACING,WATERLOGGED);
    }

    @Override
    public BlockEntityType<StatueBlockEntity> getBlockEntityType() {
        return ModBlocks.STATUE_BLOCK_ENTITY;
    }

    @Override
    public BlockEntityTicker<StatueBlockEntity> getTickDelegate()
    {
        return StatueBlockEntity::tick;
    }


    @Override
      public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
          return new StatueBlockEntity(pos, state);
      }

      public BlockState getPlacementState(ItemPlacementContext ctx) {
            return this.getDefaultState().with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing().getOpposite());
      }

      //Shadow Fix
      @Override
      public float getAmbientOcclusionLightLevel(BlockState state, BlockView world, BlockPos pos)
      {
            return 1.0f;
      }
      @Override
    public BlockRenderType getRenderType(BlockState state) {
        // With inheriting from BlockWithEntity this defaults to INVISIBLE, so we need to change that!
        return BlockRenderType.MODEL;
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.getFluidTickScheduler().schedule(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }


    //Work Your Magic Jhonny! Bring me that GUI!
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();
        if (!world.isClient)    {
            //This will call the createScreenHandlerFactory method from BlockWithEntity, which will return our blockEntity casted to
            //a namedScreenHandlerFactory. If your block class does not extend BlockWithEntity, it needs to implement createScreenHandlerFactory.
            NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);
 
            if (screenHandlerFactory != null) {
                if (!item.equals(ModItems.CHISEL)) {
                    //With this call the server will request the client to open the appropriate Screenhandler
                    player.openHandledScreen(screenHandlerFactory);
                }
            }
        }
        return ActionResult.SUCCESS;
    }
          //This method will drop all items onto the ground when the block is broken
    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof StatueBlockEntity) {
                ItemScatterer.spawn(world, pos, (StatueBlockEntity)blockEntity);
                // update comparators
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }
    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }
 
    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos));
    }

    static
    {
        WATERLOGGED = Properties.WATERLOGGED;
        FACING = Properties.HORIZONTAL_FACING;
		VOXEL_SHAPE_CUBE = VoxelShapes.cuboid(0f, 0f, 0f, 1f, 1f, 1);
    }
}