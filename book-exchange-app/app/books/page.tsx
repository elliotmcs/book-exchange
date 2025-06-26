import BookGrid from "../components/books/BookGrid";

export default async function Page({ searchParams }: {
    searchParams: Promise<{ [key: string]: string | string[] | undefined }>
}) {
    let { user, tag } = await searchParams
    user = typeof user === "string" ? [user] : user === undefined ? [] : user
    tag = typeof tag === "string" ? [tag] : tag === undefined ? [] : tag
    return <BookGrid tagIds={tag} userIds={user}/>
}