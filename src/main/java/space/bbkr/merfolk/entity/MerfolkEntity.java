package space.bbkr.merfolk.entity;

import java.util.UUID;

import com.google.common.collect.ImmutableList;
import org.jetbrains.annotations.Nullable;

import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.SwimNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.AbstractPiglinEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.PiglinActivity;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

public class MerfolkEntity extends AbstractPiglinEntity {
	private static final TrackedData<Boolean> BABY = DataTracker.registerData(MerfolkEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
	private static final UUID BABY_SPEED_BOOST_ID = UUID.fromString("64fe4278-0505-4346-985a-620b6d9b5a0c");
	private static final EntityAttributeModifier BABY_SPEED_BOOST = new EntityAttributeModifier(BABY_SPEED_BOOST_ID, "Baby speed boost", 0.20000000298023224D, EntityAttributeModifier.Operation.MULTIPLY_BASE);
	protected static final ImmutableList<SensorType<? extends Sensor<? super MerfolkEntity>>> SENSOR_TYPES;
	protected static final ImmutableList<MemoryModuleType<?>> MEMORY_MODULE_TYPES;

	private final SimpleInventory inventory = new SimpleInventory(8);
	private boolean cannotHunt;

	public MerfolkEntity(EntityType<? extends AbstractPiglinEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected boolean canHunt() {
		return false;
	}

	@Override
	public PiglinActivity getActivity() {
		return null;
	}

	@Override
	protected void playZombificationSound() {

	}

	public void onTrackedDataSet(TrackedData<?> data) {
		super.onTrackedDataSet(data);
		if (BABY.equals(data)) {
			this.calculateDimensions();
		}

	}

	public static DefaultAttributeContainer.Builder createMerfolkAttributes() {
		return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 16.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3499999940395355D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.0D);
	}

	@Nullable
	public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable CompoundTag entityTag) {
		if (spawnReason != SpawnReason.STRUCTURE) {
			if (world.getRandom().nextFloat() < 0.2F) {
				this.setBaby(true);
			} else if (this.isAdult()) {
				this.equipStack(EquipmentSlot.MAINHAND, this.makeInitialWeapon());
			}
		}

//		PiglinBrain.setHuntedRecently(this);
		this.initEquipment(difficulty);
		this.updateEnchantments(difficulty);
		return super.initialize(world, difficulty, spawnReason, entityData, entityTag);
	}

	private ItemStack makeInitialWeapon() {
		return (double)this.random.nextFloat() < 0.5D ? new ItemStack(Items.TRIDENT) : new ItemStack(Items.GOLDEN_SWORD);
	}

	protected boolean isDisallowedInPeaceful() {
		return false;
	}

	public boolean canImmediatelyDespawn(double distanceSquared) {
		return !this.isPersistent();
	}

	@Override
	protected EntityNavigation createNavigation(World world) {
		return new SwimNavigation(this, world);
	}

	@Override
	public boolean shouldZombify() {
		return super.shouldZombify();
	}

	@Override
	protected void mobTick() {
		super.mobTick();
	}

	static {
		SENSOR_TYPES = ImmutableList.of(
				SensorType.NEAREST_LIVING_ENTITIES,
				SensorType.NEAREST_PLAYERS,
				SensorType.NEAREST_ITEMS,
				SensorType.HURT_BY,
				SensorType.PIGLIN_SPECIFIC_SENSOR
		);
		MEMORY_MODULE_TYPES = ImmutableList.of(
				MemoryModuleType.LOOK_TARGET,
				MemoryModuleType.DOORS_TO_CLOSE,
				MemoryModuleType.MOBS,
				MemoryModuleType.VISIBLE_MOBS,
				MemoryModuleType.NEAREST_VISIBLE_PLAYER,
				MemoryModuleType.NEAREST_VISIBLE_TARGETABLE_PLAYER,
				MemoryModuleType.NEAREST_VISIBLE_ADULT_PIGLINS,
				MemoryModuleType.NEARBY_ADULT_PIGLINS,
				MemoryModuleType.NEAREST_VISIBLE_WANTED_ITEM,
				MemoryModuleType.HURT_BY,
				MemoryModuleType.HURT_BY_ENTITY,
				MemoryModuleType.WALK_TARGET,
				MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE,
				MemoryModuleType.ATTACK_TARGET,
				MemoryModuleType.ATTACK_COOLING_DOWN,
				MemoryModuleType.INTERACTION_TARGET,
				MemoryModuleType.PATH,
				MemoryModuleType.ANGRY_AT,
				MemoryModuleType.UNIVERSAL_ANGER,
				MemoryModuleType.AVOID_TARGET,
				MemoryModuleType.ADMIRING_ITEM,
				MemoryModuleType.TIME_TRYING_TO_REACH_ADMIRE_ITEM,
				MemoryModuleType.ADMIRING_DISABLED,
				MemoryModuleType.DISABLE_WALK_TO_ADMIRE_ITEM,
				MemoryModuleType.CELEBRATE_LOCATION,
				MemoryModuleType.DANCING,
				MemoryModuleType.HUNTED_RECENTLY,
				MemoryModuleType.NEAREST_VISIBLE_BABY_HOGLIN,
				MemoryModuleType.NEAREST_VISIBLE_NEMESIS,
				MemoryModuleType.NEAREST_VISIBLE_ZOMBIFIED,
				MemoryModuleType.RIDE_TARGET,
				MemoryModuleType.VISIBLE_ADULT_PIGLIN_COUNT,
				MemoryModuleType.VISIBLE_ADULT_HOGLIN_COUNT,
				MemoryModuleType.NEAREST_VISIBLE_HUNTABLE_HOGLIN,
				MemoryModuleType.NEAREST_TARGETABLE_PLAYER_NOT_WEARING_GOLD,
				MemoryModuleType.NEAREST_PLAYER_HOLDING_WANTED_ITEM,
				MemoryModuleType.ATE_RECENTLY,
				MemoryModuleType.NEAREST_REPELLENT
		);
	}
}
