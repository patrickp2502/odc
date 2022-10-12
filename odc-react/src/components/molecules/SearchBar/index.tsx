import { SearchSharp } from '@mui/icons-material'
import { Grid, Button, TextField } from '@mui/material'

const SearchBar: React.FC = () => {
    return (<Grid container
        display={'inline-flex'}
        justifyContent={'flex-start'}>

        <Grid item xs={4}>
            <TextField label="Fuzzy Search" />
        </Grid>
        <Grid item xs={1} display={'flex'}>
            <Button type='submit'>
                <SearchSharp sx={{ color: "black" }}></SearchSharp>
            </Button>
        </Grid>
    </Grid>
    )

}


export default SearchBar