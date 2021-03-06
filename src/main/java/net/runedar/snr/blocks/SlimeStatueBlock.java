package net.runedar.snr.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.ShapeContext;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class SlimeStatueBlock extends StatueMain{
      public SlimeStatueBlock() {
      super(FabricBlockSettings.of(Material.STONE)
	      .strength(1.5f, 0.6f)
	      .breakByTool(FabricToolTags.PICKAXES)
	      .sounds(BlockSoundGroup.STONE)
	      .nonOpaque());
      }
	static        VoxelShape                     VOXEL_SHAPE_NORTH;
	static        VoxelShape                     VOXEL_SHAPE_WEST;

	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context)
	{
	    Direction facing = state.get(FACING);
	    {
			return switch (facing) {
				case NORTH, SOUTH -> VOXEL_SHAPE_NORTH;
				case EAST, WEST -> VOXEL_SHAPE_WEST;
				default -> VOXEL_SHAPE_CUBE;
			};
	    }
	}
	static {
		VOXEL_SHAPE_NORTH = VoxelShapes.cuboid(0.2f, 0f, 0.1f, 0.8f, 0.5f, 0.9f);
		VOXEL_SHAPE_WEST = VoxelShapes.cuboid(0.1f, 0f, 0.2f, 0.8f, 0.5f, 0.8f);
	}
}