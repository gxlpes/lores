package com.api.lores.generic;

import org.springframework.data.jpa.repository.JpaRepository;

import java.rmi.server.UID;
import java.util.UUID;

interface GenericRepository<T> extends JpaRepository<T, UUID> {
}