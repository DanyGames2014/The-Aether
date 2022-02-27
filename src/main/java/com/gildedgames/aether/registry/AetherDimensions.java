package com.gildedgames.aether.registry;

import com.gildedgames.aether.level.dimension.Aether;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.registry.DimensionRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.mod.entrypoint.EventBusPolicy;
import net.modificationstation.stationapi.api.registry.DimensionContainer;
import net.modificationstation.stationapi.api.registry.DimensionRegistry;
import net.modificationstation.stationapi.api.registry.Identifier;

import static com.gildedgames.aether.Aether.of;

@Entrypoint(eventBus = @EventBusPolicy(registerInstance = false))
public class AetherDimensions {

    @EventListener
    private static void registerDimensions(DimensionRegistryEvent event) {
        DimensionRegistry r = event.registry;
        r.register(THE_AETHER = of("the_aether"), new DimensionContainer<>(Aether::new));
    }

    public static Identifier THE_AETHER;
}
