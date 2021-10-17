package com.dataart.coreservice.repository

import com.dataart.coreservice.model.Event
import org.springframework.data.repository.CrudRepository

interface EventRepository : CrudRepository<Event, Long>
