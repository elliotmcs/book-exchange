'use server'

import { Grid, Card, CardContent, Typography, CardHeader, Avatar } from "@mui/material"
import React, { Key } from 'react'
import { red } from '@mui/material/colors'

import styles from "./BookGrid.module.css"

type Book = {
  id: String,
  title: String,
  author: String,
  isbn: number,
  quality: number,
  userId: String,
  tagIds: String[],
  isPublic: boolean
}

type User = {
    id: String,
    username: String,
    email: String,
    rating: number,
    isActive: boolean
}

type BookWithUser = {
    user: User
} & Book;

type GridItemProps = {
    key: Key,
    book: BookWithUser
    onClick: Function
}

type BookGridProps = {
    userIds: String[],
    tagIds: String[]
}

const getAuthHeaders = () => {
    let headers = new Headers();
    headers.append("Authorization", "Basic " + Buffer.from(process.env.SERVER_USER + ":" + process.env.SERVER_PASSWORD).toString('base64'))
    return headers
}

const getBooks = (async () => {
    const headers = getAuthHeaders()
    const res = await fetch(`${process.env.SERVER_URL}/api/books`, { headers })
    const books: Book[] = await res.json()
    return books
})

const getUsers = (async () => {
    const headers = getAuthHeaders()
    const res = await fetch(`${process.env.SERVER_URL}/api/users`, { headers })
    const users: User[] = await res.json()
    return users
})

const getBooksWithUsers = (async (): Promise<BookWithUser[]> => {
    const books: Book[] = await getBooks()
    const users: User[] = await getUsers()

    return books.map(book => {
        const user: User | undefined = users.find(u => u.id == book.userId)
        if (!user) return
        let bookWithUser: BookWithUser = {
            user: user,
            ...book
        }
        return bookWithUser
    }).filter(b => b !== undefined)
})

const GridItem = ( props: GridItemProps ) => {
    const book: BookWithUser = props.book
    return <Card sx={{width: 200, height: 200}}>
        <CardHeader
            avatar={
            <Avatar aria-label="user">
                {book.user.username[0]}
            </Avatar>
            }
            title={book.title}
        />
        <CardContent>

        </CardContent>
    </Card>
}

const BookGrid = async (props: BookGridProps) => {
    const books: BookWithUser[] = await getBooksWithUsers()

    return <Grid container spacing={2}>
        { books.filter((book: BookWithUser) => {
            let include = true
            if (props.tagIds.length > 0) {
                include &&= book.tagIds.filter(tagId => props.tagIds.includes(tagId)).length > 0
            }
            if (props.userIds.length > 0) {
                include &&= props.userIds.includes(book.userId)
            }
            return include
        }).map((book: BookWithUser) => 
            <GridItem key={book.id.toString()} book={book} onClick={() => {}} />
        )}
    </Grid>
}

export default BookGrid