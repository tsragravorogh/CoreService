package com.dataart.coreservice.model

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "users")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var surname: String,

    @Column(nullable = false)
    var email: String,

    @Column(nullable = false)
    var password: String,

    var linkAva: String,

    @CreatedDate
    @Column(nullable = false)
    var createdDt: Instant,

    @LastModifiedDate
    var updatedDt: Instant,

    //  owner side
    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinTable(
        name = "users_events",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "event_id")]
    )
    var events: MutableList<Event>,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
    var messages: MutableList<Message>,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
    var photos: MutableList<Photo>,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
    var likeEvents: MutableList<LikeEvent>,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
    var likePhotos: MutableList<LikePhoto>,

    )
