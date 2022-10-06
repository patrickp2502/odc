import { Grid, Fab } from '@mui/material'
import AddIcon from '@mui/icons-material/Add'
import React from 'react'
import SearchBar from '../../molecules/SearchBar'

interface SubNavProps {
    onClickAddButton?: (param?: any) => void
}

//TODO create Function for react
const SubNav: React.FC<SubNavProps> = () => {
    return (
        <Grid container display={"flex"} alignItems="center">
            <Grid item xs={1}
                display={"flex"}
                justifyContent={"center"}
            >
                <Fab
                    size="large"
                    sx={{
                        color: "white",
                        bgcolor: "orange"
                    }}>
                    <AddIcon />
                </Fab>
            </Grid>
            <Grid item xs={10}>
                <SearchBar />
            </Grid>
        </Grid>
    )
}

export default SubNav