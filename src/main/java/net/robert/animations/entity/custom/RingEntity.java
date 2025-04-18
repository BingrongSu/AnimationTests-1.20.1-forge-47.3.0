package net.robert.animations.entity.custom;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.Minecart;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RingEntity extends Entity {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final EntityDataAccessor<Optional<UUID>> OWNER_UUID =
            SynchedEntityData.defineId(RingEntity.class, EntityDataSerializers.OPTIONAL_UUID);
    public static UUID uu1d;

    public RingEntity(EntityType<? extends RingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(OWNER_UUID, Optional.empty());
    }
    public void setOwner(Player player) {
        this.entityData.set(OWNER_UUID, Optional.of(player.getUUID()));
    }

    public Optional<UUID> getOwnerUUID() {
        return this.entityData.get(OWNER_UUID);
    }

    // 3) NBT save/load so ownership persists
    @Override
    protected void addAdditionalSaveData(CompoundTag compound) {
        getOwnerUUID().ifPresent(uuid -> compound.putUUID("OwnerUUID", uuid));
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compound) {
        if (compound.hasUUID("OwnerUUID")) {
            this.entityData.set(OWNER_UUID, Optional.of(compound.getUUID("OwnerUUID")));
        }
    }
    @Override
    public void tick() {
        super.tick();
        // 1) If no owner yet, scan for players intersecting our AABB
        if (getOwnerUUID().isEmpty()) {
            // Inflate slightly if you want a looser trigger
            AABB triggerBox = this.getBoundingBox().inflate(0.1D);
            List<Player> players = this.level().getEntitiesOfClass(
                    Player.class,
                    triggerBox,
                    p -> !p.isSpectator()  // ignore spectators, for example
            );
            if (!players.isEmpty()) {
                Player first = players.get(0);
                this.setOwner(first);
                //if (!this.level().isClientSide) {
                    this.startRiding(first, true);
                   // LOGGER.info("RingEntity {} started riding {}", this.getId(), first.getName().getString());
                //}
                // Optionally: send a packet or play a sound to confirm binding
            }
        }else {
            getOwnerUUID().ifPresent(uuid -> {
                uu1d=uuid;
                Player owner = this.level().getPlayerByUUID(uuid);
                if (owner != null && !this.isPassenger()) {
                    //if (!this.level().isClientSide) {
                        this.startRiding(owner, true);
                       // LOGGER.info("RingEntity {} re-mounted to {}", this.getId(), owner.getName().getString());
                    //}
                }
            });
        }
    }

    public double getBobbingOffset() {
        double frequency = 0.1; // Adjust for speed of bobbing
        double amplitude = 0.05;  // Adjust for height of bobbing
        return Math.sin(this.tickCount * frequency) * amplitude;
    }

    @Override
    public double getMyRidingOffset() {
        Player player = this.level().getPlayerByUUID(uu1d);
        assert player != null;
        if(player.getVehicle() instanceof Boat || player.getVehicle() instanceof Minecart){
            return -0.9;
        }
        return -1.1;
    }
}

