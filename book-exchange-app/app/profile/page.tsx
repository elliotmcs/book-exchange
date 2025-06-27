import banner from '@/public/profile_banner.jpg'
import { Avatar, Box, Grid, Typography } from '@mui/material'
import Image from 'next/image'

import styles from "./profile.module.css"
import BookGrid from '../components/books/BookGrid'

export default function Page() {
    return <div>
        {/* <img src={"/profile_banner.jpg"} alt="Profile banner" width="100%" height="auto" style={{ filter: "brightness(50%)" }}/> */}
        <Box className={styles.profileBannerOverlay}>
            <img className={styles.profileBannerImage} width="100%" src="/profile_banner.jpg" alt="Profile banner" />
            <Avatar className={styles.avatar}></Avatar>
            <div className={styles.profileStatsContainer}>
                <Grid container direction="row" alignItems="center" justifyContent="center" spacing={10}>
                    <Grid container direction="column" alignItems="center" justifyContent="center" spacing={1}>
                        <Typography variant="h3" color="white">26</Typography>
                        <Typography variant="subtitle1" color='white' justifyContent="center">Books posted</Typography>
                    </Grid>
                    <Grid container direction="column" alignItems="center" justifyContent="center" spacing={1}>
                        <Typography variant="h3" color="white">26</Typography>
                        <Typography variant="subtitle1" color='white' justifyContent="center">Books exchanged</Typography>
                    </Grid>
                </Grid>
            </div>
        </Box>
        <BookGrid />
    </div>
}