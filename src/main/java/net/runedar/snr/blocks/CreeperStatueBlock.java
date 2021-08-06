package net.runedar.snr.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Material;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.sound.BlockSoundGroup;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;

public class CreeperStatueBlock extends TallStatue {
      public CreeperStatueBlock() {
      super(FabricBlockSettings.of(Material.STONE)
	      .strength(1.5f, 0.6f)
	      .breakByTool(FabricToolTags.PICKAXES)
	      .sounds(BlockSoundGroup.STONE)
	      .nonOpaque());
	}
	static        VoxelShape                     VOXEL_SHAPE_NORTH;
	static        VoxelShape                     VOXEL_SHAPE_NORTHU;
	static        VoxelShape                     VOXEL_SHAPE_WEST;
	static        VoxelShape                     VOXEL_SHAPE_WESTU;

	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context)
	{
		Direction facing = state.get(FACING);
		//I would like to shorten this into boolean with one switch statement
		DoubleBlockHalf doubleBlockHalf = state.get(HALF);
		if (doubleBlockHalf == DoubleBlockHalf.UPPER) {
			{
				return switch (facing) {
					case NORTH, SOUTH -> VOXEL_SHAPE_NORTHU;
					case EAST, WEST -> VOXEL_SHAPE_WESTU;
					default -> VOXEL_SHAPE_CUBE;
				};
			}
		}
			else {
			return switch (facing) {
				case NORTH, SOUTH -> VOXEL_SHAPE_NORTH;
				case EAST, WEST -> VOXEL_SHAPE_WEST;
				default -> VOXEL_SHAPE_CUBE;
			};
		}
	}
	static {
		VOXEL_SHAPE_NORTH = VoxelShapes.cuboid(0.2f, 0f, 0.1f, 0.8f, 1.7f, 0.9f);
		VOXEL_SHAPE_WEST = VoxelShapes.cuboid(0.1f, 0f, 0.2f, 0.9f, 1.7f, 0.8);
		VOXEL_SHAPE_NORTHU = VoxelShapes.cuboid(0.2f, -1f, 0.1f, 0.8f, 0.7f, 0.9f);
		VOXEL_SHAPE_WESTU = VoxelShapes.cuboid(0.1f, -1f, 0.2f, 0.9f, 0.7f, 0.8);

	}

}