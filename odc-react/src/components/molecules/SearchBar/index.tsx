import { SearchSharp } from '@mui/icons-material'
import { Grid, Button, TextField } from '@mui/material'

const SearchBar: React.FC = () => {
    return (<Grid container>
        <Grid item xs={1} display='flex' justifyItems="center">
            <Button type='submit'>
                <SearchSharp sx={{ color: "black" }}></SearchSharp>
            </Button>
        </Grid>
        <Grid item>
            <TextField label="Fuzzy Search" />
        </Grid>
    </Grid>
    )

}


export default SearchBar