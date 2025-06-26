import banner from '@/public/profile_banner.jpg'
import { Box } from '@mui/material'
import Image from 'next/image'

export default function Page() {
    return <Box sx={{width:"100%", height:"400px"}}>
        <Image src='/profile_banner.jpg' alt="Profile banner" fill />
    </Box>
}