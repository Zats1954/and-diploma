package ru.netology.nework.controller

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import ru.netology.nework.service.PostService

@RestController
@RequestMapping("/api/my/wall", "/api/slow/my/wall")
class MyWallController(private val service: PostService) {
    @GetMapping
    fun getAll() = service.getAllMy()

    @GetMapping("/{id:\\d+}")
    fun getById(@PathVariable id: Long) = service.getMyById(id)

    @GetMapping("/latest")
    fun getLatest(@RequestParam count: Int) = service.getMyLatest(count)

    @GetMapping("/{id}/newer")
    fun getNewer(@PathVariable id: Long) = service.getMyNewer(id)

    @GetMapping("/{id}/before")
    fun getBefore(@PathVariable id: Long, @RequestParam count: Int) = service.getMyBefore(id, count)

    @GetMapping("/{id}/after")
    fun getAfter(@PathVariable id: Long, @RequestParam count: Int) = service.getMyAfter(id, count)

    @PostMapping("/{id}/likes")
    @PreAuthorize("hasRole('USER')")
    fun likeById(@PathVariable id: Long) = service.likeById(id)

    @DeleteMapping("/{id}/likes")
    @PreAuthorize("hasRole('USER')")
    fun unlikeById(@PathVariable id: Long) = service.unlikeById(id)
}