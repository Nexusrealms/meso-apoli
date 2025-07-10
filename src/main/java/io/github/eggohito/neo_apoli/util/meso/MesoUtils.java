package io.github.eggohito.neo_apoli.util.meso;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import io.github.eggohito.neo_apoli.action.ActionEntry;
import io.github.eggohito.neo_apoli.action.category.ActionCategory;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.explosion.Explosion;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MesoUtils {
    public static final PacketCodec<ByteBuf, Vec3d> VEC3D_PACKET_CODEC = new PacketCodec<ByteBuf, Vec3d>() {
        public Vec3d decode(ByteBuf buf) {
            return new Vec3d(buf.readDouble(), buf.readDouble(), buf.readDouble());
        }

        public void encode(ByteBuf buf, Vec3d vec) {
            buf.writeDouble(vec.getX());
            buf.writeDouble(vec.getY());
            buf.writeDouble(vec.getZ());
        }
    };

    public static <M extends Map<?, ?>> Codec<M> nonEmptyMap(Codec<M> codec) {
        return codec.validate((map) -> map.isEmpty() ? DataResult.error(() -> "Map must not be empty") : DataResult.success(map));
    }
    public static void explode(Explosion explosion, World world) {
        explosion.collectBlocksAndDamageEntities();
        List<BlockPos> list = explosion.getAffectedBlocks();
        if (explosion.shouldDestroy()) {
            explosion.affectWorld(true);
        }
    }
    public static <T> PacketCodec<ByteBuf, TagKey<T>> tagKeyPacketCodec(RegistryKey<? extends Registry<T>> registryRef) {
        return Identifier.PACKET_CODEC.xmap((id) -> TagKey.of(registryRef, id), TagKey::id);
    }
    public static ItemActionResult toItemActionResult(ActionResult result){
        return switch (result) {
            case SUCCESS, SUCCESS_NO_ITEM_USED:
                yield ItemActionResult.SUCCESS;
            case CONSUME:
                yield ItemActionResult.CONSUME;
            case CONSUME_PARTIAL:
                yield ItemActionResult.CONSUME_PARTIAL;
            case PASS:
                yield ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            case FAIL:
                yield ItemActionResult.FAIL;

        };
    }
}
