package dev.enemeez.simplefarming.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import com.mojang.serialization.MapCodec;

import java.util.function.Supplier;

public abstract class GrowableBushBlock extends BushBlock implements BonemealableBlock {
    public static final int MAX_AGE = 3;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    private final Supplier<Item> OUTPUT;

    public GrowableBushBlock(Properties pProperties, Supplier<Item> output) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
        this.OUTPUT = output;
    }

    @Override
    abstract public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext);

    @Override
    protected abstract MapCodec<? extends GrowableBushBlock> codec();

    @Override
    public boolean isValidBonemealTarget(LevelReader pLevel, BlockPos pPos, BlockState pState) {
        return pState.getValue(AGE) < MAX_AGE;
    }

    @Override
    public boolean isBonemealSuccess(Level pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        pLevel.setBlock(pPos, pState.setValue(AGE, Math.min(MAX_AGE, pState.getValue(AGE) + 1)), 2);
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader pLevel, BlockPos pPos, BlockState pState) {
        return new ItemStack(OUTPUT.get());
    }

    @Override
    public boolean isRandomlyTicking(BlockState pState) {
        return pState.getValue(AGE) < MAX_AGE;
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        final int age = pState.getValue(AGE);
        if (age < MAX_AGE && pLevel.getRawBrightness(pPos.above(), 0) >= 9 && pRandom.nextInt(5) == 0) {
            BlockState blockstate = pState.setValue(AGE, age + 1);
            pLevel.setBlock(pPos, blockstate, 2);
            pLevel.gameEvent(GameEvent.BLOCK_CHANGE, pPos, GameEvent.Context.of(blockstate));
        }
    }

    @Override
    public ItemInteractionResult useItemOn(ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        final int age = pState.getValue(AGE);
        if (age != MAX_AGE && pStack.is(Items.BONE_MEAL)) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        } else if (age == MAX_AGE) {
            harvest(pState, pLevel, pPos, pPlayer);
            return ItemInteractionResult.sidedSuccess(pLevel.isClientSide);
        } else {
            return super.useItemOn(pStack, pState, pLevel, pPos, pPlayer, pHand, pHit);
        }
    }

    @Override
    public InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHit) {
        if (pState.getValue(AGE) == MAX_AGE) {
            harvest(pState, pLevel, pPos, pPlayer);
            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        } else {
            return super.useWithoutItem(pState, pLevel, pPos, pPlayer, pHit);
        }
    }

    private void harvest(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer) {
        popResource(pLevel, pPos, new ItemStack(OUTPUT.get()));
        pLevel.playSound(null, pPos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + pLevel.random.nextFloat() * 0.4F);
        BlockState blockstate = pState.setValue(AGE, 0);
        pLevel.setBlock(pPos, blockstate, 2);
        pLevel.gameEvent(GameEvent.BLOCK_CHANGE, pPos, GameEvent.Context.of(pPlayer, blockstate));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AGE);
    }
}
