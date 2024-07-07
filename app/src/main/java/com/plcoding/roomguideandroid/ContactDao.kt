package com.plcoding.roomguideandroid

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {

    @Upsert
    suspend fun upsertContact(contact: Contact)                                 //Upsert: Update(if already exist) or Insert, Alternative: @Insert(onConflict)
                                                                                //suspend: run it in a coroutine, and block it until database operation finish
    @Delete
    suspend fun deleteContact(contact: Contact)

    @Query("SELECT * FROM contact ORDER BY firstName ASC")                      //ASC = Ascending
    fun getContactsOrderedByFirstName(): Flow<List<Contact>>                    //Flow detects changes, if new contact added, will get the new list

    @Query("SELECT * FROM contact ORDER BY lastName ASC")
    fun getContactsOrderedByLastName(): Flow<List<Contact>>

    @Query("SELECT * FROM contact ORDER BY phoneNumber ASC")
    fun getContactsOrderedByPhoneNumber(): Flow<List<Contact>>
}